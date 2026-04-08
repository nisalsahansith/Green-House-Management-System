import "reflect-metadata";
import express, { type Request, type Response } from 'express';
import axios from 'axios';
import { Eureka } from 'eureka-js-client';
import { DataSource, Entity, PrimaryGeneratedColumn, Column, CreateDateColumn } from "typeorm";

// --- Database Configuration & Entity ---

@Entity()
class AutomationLog {
    @PrimaryGeneratedColumn()
    id!: number;

    @Column("varchar")
    zoneId!: string;

    @Column("float")
    temperature!: number;

    @Column("varchar")
    action!: string;

    @CreateDateColumn()
    createdAt!: Date;
}

const AppDataSource = new DataSource({
    type: "mysql",
    host: "localhost",
    port: 3306,
    username: "root", // Replace with your DB user
    password: "Ijse@1234", // Replace with your DB password
    database: "agms_automation_db",
    entities: [AutomationLog],
    synchronize: true, // Automatically creates tables - use false in production
    logging: false,
});

// --- App Setup ---

const app = express();
app.use(express.json());
const PORT = 8083;

interface TelemetryData {
    zoneId: string;
    value: { temperature: number };
}

interface ZoneDTO {
    id: number;
    minTemp: number;
    maxTemp: number;
}

// --- Logic with Persistence ---

app.post('/api/automation/process', async (req: Request<{}, {}, TelemetryData>, res: Response) => {
    const { zoneId, value } = req.body;
    const currentTemp = value.temperature;

    try {
        // 1. Fetch limits from Zone Service
        const response = await axios.get<ZoneDTO>(`http://localhost:8081/api/zones/${zoneId}`);
        const zone = response.data;

        // 2. Decide Action
        let action = "STABLE";
        if (currentTemp > zone.maxTemp) {
            action = "TURN_FAN_ON";
        } else if (currentTemp < zone.minTemp) {
            action = "TURN_HEATER_ON";
        }

        // 3. Persist to MySQL
        const logRepo = AppDataSource.getRepository(AutomationLog);
        const newLog = logRepo.create({
            zoneId,
            temperature: currentTemp,
            action
        });
        await logRepo.save(newLog);

        console.log(`[DB Saved] Zone: ${zoneId} | Action: ${action}`);
        
        res.status(200).json({ status: "Processed", action, timestamp: new Date() });

    } catch (error) {
        console.error("Automation error:", error);
        res.status(500).send("Internal Server Error");
    }
});

// --- Eureka & Server Start ---

const eurekaClient = new Eureka({
    instance: {
        app: 'AUTOMATION-CONTROL-SERVICE',
        hostName: '127.0.0.1',
        ipAddr: '127.0.0.1',
        port: { '$': PORT, '@enabled': true },
        vipAddress: 'AUTOMATION-CONTROL-SERVICE',
        dataCenterInfo: {
            '@class': 'com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo',
            name: 'MyOwn',
        },
    },
    eureka: {
        host: '127.0.0.1',
        port: 8761,
        servicePath: '/eureka/apps/',
    },
});

AppDataSource.initialize()
    .then(() => {
        console.log(" MySQL Connected");
        app.listen(PORT, () => {
            console.log(` Service running on port ${PORT}`);
            eurekaClient.start((err: any) => {
                console.log(err || " Registered with Eureka");
            });
        });
    })
    .catch((error: any) => console.log("DB Connection Error: ", error));