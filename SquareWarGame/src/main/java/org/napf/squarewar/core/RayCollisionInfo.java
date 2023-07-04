package org.napf.squarewar.core;

public class RayCollisionInfo {
	private double contactPointX, contactPointY;
	private double contactNormalX, contactNormalY;
	private double tHitNear;
	
	public RayCollisionInfo(double contactPointX, double contactPointY,
			double contactNormalX, double contactNormalY, double tHitNear) {
		this.contactPointX = contactPointX;
		this.contactPointY = contactPointY;
		this.contactNormalX = contactNormalX;
		this.contactNormalY = contactNormalY;
		this.tHitNear = tHitNear;
	}
	
	public double getContactPointX() {
		return contactPointX;
	}
	public void setContactPointX(double contactPointX) {
		this.contactPointX = contactPointX;
	}
	public double getContactPointY() {
		return contactPointY;
	}
	public void setContactPointY(double contactPointY) {
		this.contactPointY = contactPointY;
	}
	public double getContactNormalX() {
		return contactNormalX;
	}
	public void setContactNormalX(double contactNormalX) {
		this.contactNormalX = contactNormalX;
	}
	public double getContactNormalY() {
		return contactNormalY;
	}
	public void setContactNormalY(double contactNormalY) {
		this.contactNormalY = contactNormalY;
	}
	public double gettHitNear() {
		return tHitNear;
	}
	public void settHitNear(double tHitNear) {
		this.tHitNear = tHitNear;
	}
	
	
}
