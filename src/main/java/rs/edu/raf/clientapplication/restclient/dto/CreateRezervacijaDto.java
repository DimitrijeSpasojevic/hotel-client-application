package rs.edu.raf.clientapplication.restclient.dto;


public class CreateRezervacijaDto {

    private Long tipSobeId;
    private Long pocetniTerminId;
    private Long krajnjiTerminId;
    private Long userId;

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
}
