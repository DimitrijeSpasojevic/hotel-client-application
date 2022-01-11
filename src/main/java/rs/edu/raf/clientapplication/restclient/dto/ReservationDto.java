package rs.edu.raf.clientapplication.restclient.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ReservationDto {
    private Long id;
    private Long tipSobeId;
    private String tipSobe;
    private Long pocetniTerminId;
    private LocalDate pocetniTerminDatum;
    private Long krajnjiTerminId;
    private LocalDate krajnjiTerminDatum;
    private Long userId;
    private String userName;
    private BigDecimal cena;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTipSobeId() {
        return tipSobeId;
    }

    public void setTipSobeId(Long tipSobeId) {
        this.tipSobeId = tipSobeId;
    }

    public Long getPocetniTerminId() {
        return pocetniTerminId;
    }

    public void setPocetniTerminId(Long pocetniTerminId) {
        this.pocetniTerminId = pocetniTerminId;
    }

    public Long getKrajnjiTerminId() {
        return krajnjiTerminId;
    }

    public void setKrajnjiTerminId(Long krajnjiTerminId) {
        this.krajnjiTerminId = krajnjiTerminId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getCena() {
        return cena;
    }

    public void setCena(BigDecimal cena) {
        this.cena = cena;
    }

    public String getTipSobe() {
        return tipSobe;
    }

    public void setTipSobe(String tipSobe) {
        this.tipSobe = tipSobe;
    }

    public LocalDate getPocetniTerminDatum() {
        return pocetniTerminDatum;
    }

    public void setPocetniTerminDatum(LocalDate pocetniTerminDatum) {
        this.pocetniTerminDatum = pocetniTerminDatum;
    }

    public LocalDate getKrajnjiTerminDatum() {
        return krajnjiTerminDatum;
    }

    public void setKrajnjiTerminDatum(LocalDate krajnjiTerminDatum) {
        this.krajnjiTerminDatum = krajnjiTerminDatum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
