package br.com.carv.parking.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "tb_user_info")
public class UserInfoSystem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String userAgent;

    @Column(nullable = false)
    private String internetProtocol;

    @Column(nullable = false)
    private String locale;

    @OneToOne(mappedBy = "userInfoSystem")
    private UserSystem userSystem;

    public UserInfoSystem() {}

    public UserInfoSystem(String userAgent, String internetProtocol, String locale) {
        this.userAgent = userAgent;
        this.internetProtocol = internetProtocol;
        this.locale = locale;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getInternetProtocol() {
        return internetProtocol;
    }

    public void setInternetProtocol(String internetProtocol) {
        this.internetProtocol = internetProtocol;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public UserSystem getUserSystem() {
        return userSystem;
    }

    public void setUserSystem(UserSystem userSystem) {
        this.userSystem = userSystem;
    }
}
