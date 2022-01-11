package rs.edu.raf.clientapplication.restclient.dto;

import java.math.BigDecimal;

public class TipSobeDto {
    private Long id;
    private String ime;
    private BigDecimal cena;
    private int pocetakOpsegaSoba;
    private int krajOpsegaSoba;
    private Long hotelId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public BigDecimal getCena() {
        return cena;
    }

    public void setCena(BigDecimal cena) {
        this.cena = cena;
    }

    public int getPocetakOpsegaSoba() {
        return pocetakOpsegaSoba;
    }

    public void setPocetakOpsegaSoba(int pocetakOpsegaSoba) {
        this.pocetakOpsegaSoba = pocetakOpsegaSoba;
    }

    public int getKrajOpsegaSoba() {
        return krajOpsegaSoba;
    }

    public void setKrajOpsegaSoba(int krajOpsegaSoba) {
        this.krajOpsegaSoba = krajOpsegaSoba;
    }

    public Long getHotelId() {
        return this.hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }
}
