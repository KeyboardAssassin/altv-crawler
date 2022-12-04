package com.altvstatistics.web.entity;


import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "altv_online_monitoring")
public class AltvOnlineMonitoringEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, updatable = false, columnDefinition = "serial")
    private Long id;

    @Column(name = "server_id", nullable = false)
    private Long serverId;

    @Column(name = "amount_players", nullable = false)
    private Long amountPlayers;


    @Column(name = "last_update", nullable = false)
    private Long lastUpdate;

    @Column(name = "timestamp", nullable = false)
    private Long timestamp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getServerId() {
        return serverId;
    }

    public void setServerId(Long serverId) {
        this.serverId = serverId;
    }

    public Long getAmountPlayers() {
        return amountPlayers;
    }

    public void setAmountPlayers(Long amountPlayers) {
        this.amountPlayers = amountPlayers;
    }

    public Long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "AltvOnlineMonitoring{" +
                "id='" + id + '\'' +
                ", serverId=" + serverId +
                ", amountPlayers=" + amountPlayers +
                ", lastUpdate=" + lastUpdate +
                ", timestamp=" + timestamp +
                '}';
    }
}