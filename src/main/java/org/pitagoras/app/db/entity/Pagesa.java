package org.pitagoras.app.db.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class Pagesa {
    private Long id;
    private Long studentId;
    private Date dataEFillimit;
    private Date dataEMbarimit;
    private Boolean eshtePaguar;
    private Timestamp paguarMe;

    public Pagesa(Long id, Long studentId, Date dataEFillimit, Date dataEMbarimit, Boolean eshtePaguar, Timestamp paguarMe) {
        this.id = id;
        this.studentId = studentId;
        this.dataEFillimit = dataEFillimit;
        this.dataEMbarimit = dataEMbarimit;
        this.eshtePaguar = eshtePaguar;
        this.paguarMe = paguarMe;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Date getDataEFillimit() {
        return dataEFillimit;
    }

    public void setDataEFillimit(Date dataEFillimit) {
        this.dataEFillimit = dataEFillimit;
    }

    public Date getDataEMbarimit() {
        return dataEMbarimit;
    }

    public void setDataEMbarimit(Date dataEMbarimit) {
        this.dataEMbarimit = dataEMbarimit;
    }

    public Boolean getEshtePaguar() {
        return eshtePaguar;
    }

    public void setEshtePaguar(Boolean eshtePaguar) {
        this.eshtePaguar = eshtePaguar;
    }

    public Timestamp getPaguarMe() {
        return paguarMe;
    }

    public void setPaguarMe(Timestamp paguarMe) {
        this.paguarMe = paguarMe;
    }

    @Override
    public String toString() {
        return "Pagesa{" + "id=" + id + ", studentId=" + studentId + ", dataEFillimit='" + dataEFillimit + '\'' + ", dataEMbarimit='" + dataEMbarimit + '\'' + ", eshtePaguar=" + eshtePaguar + ", paguarMe='" + paguarMe + '\'' + '}';
    }
}