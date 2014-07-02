package db.myorder;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Order implements Serializable {
	 public String id=null;
	 public String date;
	 public int fanQie;
	 public int laoYa;
	 public int niuRou;
	 public int xianYu;
	 public int xiaRen;
	 public int sanXian;
	 public int qinCai;
	 public int guoTie;
	 public int sum;
	 public String address;
	 public Order(){	 
	 }
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getFanQie() {
		return fanQie;
	}
	public void setFanQie(int fanQie) {
		this.fanQie = fanQie;
	}
	public int getLaoYa() {
		return laoYa;
	}
	public void setLaoYa(int laoYa) {
		this.laoYa = laoYa;
	}
	public int getNiuRou() {
		return niuRou;
	}
	public void setNiuRou(int niuRou) {
		this.niuRou = niuRou;
	}
	public int getXianYu() {
		return xianYu;
	}
	public void setXianYu(int xianYu) {
		this.xianYu = xianYu;
	}
	public int getXiaRen() {
		return xiaRen;
	}
	public void setXiaRen(int xiaRen) {
		this.xiaRen = xiaRen;
	}
	public int getSanXian() {
		return sanXian;
	}
	public void setSanXian(int sanXian) {
		this.sanXian = sanXian;
	}
	public int getQinCai() {
		return qinCai;
	}
	public void setQinCai(int qinCai) {
		this.qinCai = qinCai;
	}
	public int getGuoTie() {
		return guoTie;
	}
	public void setGuoTie(int guoTie) {
		this.guoTie = guoTie;
	}
	
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String date) {
		this.address = date;
	}
}

















