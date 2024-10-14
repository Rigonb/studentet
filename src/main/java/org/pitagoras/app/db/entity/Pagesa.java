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

    public void setDataEFillimit(String dataEFillimit) {
        this.dataEFillimit = Date.valueOf(dataEFillimit);

    }


    public Date getDataEMbarimit() {
        return dataEMbarimit;
    }

    public void DatesetDataEMbarimit(Date dataEMbarimit) {
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



    public String toString() {
        return "Pagesa{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", dataEFillimit='" + dataEFillimit + '\'' +
                ", dataEMbarimit='" + dataEMbarimit + '\'' +
                ", eshtePaguar=" + eshtePaguar +
                ", paguarMe='" + paguarMe + '\'' +
                ", pagesat=" +
                '}';
    }

}