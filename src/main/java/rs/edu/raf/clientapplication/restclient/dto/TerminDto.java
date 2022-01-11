package rs.edu.raf.clientapplication.restclient.dto;

import java.time.LocalDate;

public class TerminDto {
    private Long id;
    private LocalDate datum;
    private int brojSlobodnihSoba;
    private Long tipSobeId;
    private String hotel;
    private String tipSobe;

    public Long getTipSobeId() {
        return tipSobeId;
    }

    public void setTipSobeId(Long tipSobeId) {
        this.tipSobeId = tipSobeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public int getBrojSlobodnihSoba() {
        return brojSlobodnihSoba;
    }

    public void setBrojSlobodnihSoba(int brojSlobodnihSoba) {
        this.brojSlobodnihSoba = brojSlobodnihSoba;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public String getTipSobe() {
        return tipSobe;
    }

    public void setTipSobe(String tipSobe) {
        this.tipSobe = tipSobe;
    }
}
