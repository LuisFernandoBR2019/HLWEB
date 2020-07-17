package com.ptc.helplife.Entity;

public class TipoSanguineo {

	private Long id;
    private String tipoSangue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoSangue() {
        return tipoSangue;
    }

    public void setTipoSangue(String tipoSangue) {
        this.tipoSangue = tipoSangue;
    }

    @Override
    public String toString() {
        return "TipoSanguineo{" + "id=" + id + ", tiposangue=" + tipoSangue + '}';
    }
}
