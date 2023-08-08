package com.bilgeadam;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.*;

@Entity
public class Ogrenci {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String ad;
	private String soyad;
	private Date dogumTarihi;
	private int okulNo;
	private byte sinif;

	public Ogrenci() {
	}

	public Ogrenci(String ad, String soyad, int okulNo, byte sinif, Date dogumTarihi) {
		super();
		this.ad = ad;
		this.soyad = soyad;
		this.dogumTarihi = dogumTarihi;
		this.okulNo = okulNo;
		this.sinif = sinif;
	}

	public Date getDogumTarihi() {
		return dogumTarihi;
	}

	public void setDogumTarihi(Date dogumTarihi) {
		this.dogumTarihi = dogumTarihi;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public String getSoyad() {
		return soyad;
	}

	public void setSoyad(String soyad) {
		this.soyad = soyad;
	}

	public int getOkulNo() {
		return okulNo;
	}

	public void setOkulNo(int okulNo) {
		this.okulNo = okulNo;
	}

	public byte getSinif() {
		return sinif;
	}

	public void setSinif(byte sinif) {
		this.sinif = sinif;
	}
}
