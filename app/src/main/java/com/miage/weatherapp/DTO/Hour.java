package com.miage.weatherapp.DTO;

import org.json.JSONException;
import org.json.JSONObject;

public class Hour {
    private String heure;
    private String ICON;
    private String CONDITION;
    private String CONDITION_KEY;
    private double TMP2m;
    private double DPT2m;
    private String WNDCHILL2m = null;
    private String HUMIDEX = null;
    private double RH2m;
    private double PRMSL;
    private double APCPsfc;
    private double WNDSPD10m;
    private double WNDGUST10m;
    private double WNDDIR10m;
    private String WNDDIRCARD10;
    private double ISSNOW;
    private String HCDC;
    private String MCDC;
    private String LCDC;
    private double HGT0C;
    private double KINDEX;
    private String CAPE180_0;
    private double CIN180_0;

    public String getHeure() {
        return heure;
    }

    public Hour(JSONObject jo, String h) {
        try {
            this.heure=h;
            this.ICON = jo.getString("ICON");
            this.CONDITION = jo.getString("CONDITION");
            this.CONDITION_KEY = jo.getString("CONDITION_KEY");
            this.TMP2m = jo.getDouble("TMP2m");
            this.DPT2m = jo.getDouble("DPT2m");
            this.WNDCHILL2m = jo.getString("WNDCHILL2m");
            this.HUMIDEX = jo.getString("HUMIDEX");
            this.RH2m = jo.getDouble("RH2m");
            this.PRMSL = jo.getDouble("PRMSL");
            this.APCPsfc = jo.getDouble("APCPsfc");
            this.WNDSPD10m = jo.getDouble("WNDSPD10m");
            this.WNDGUST10m = jo.getDouble("WNDGUST10m");
            this.WNDDIR10m = jo.getDouble("WNDDIR10m");
            this.WNDDIRCARD10 = jo.getString("WNDDIRCARD10");
            this.ISSNOW = jo.getDouble("ISSNOW");
            this.HCDC = jo.getString("HCDC");
            this.MCDC = jo.getString("MCDC");
            this.LCDC = jo.getString("LCDC");
            this.HGT0C = jo.getDouble("HGT0C");
            this.KINDEX = jo.getDouble("KINDEX");
            this.CAPE180_0 = jo.getString("CAPE180_0");
            this.CIN180_0 = jo.getDouble("CIN180_0");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // Getter Methods

    public String getICON() {
            return ICON;
            }

    public String getCONDITION() {
            return CONDITION;
            }

    public String getCONDITION_KEY() {
            return CONDITION_KEY;
            }

    public double getTMP2m() {
            return TMP2m;
            }

    public double getDPT2m() {
            return DPT2m;
            }

    public String getWNDCHILL2m() {
            return WNDCHILL2m;
            }

    public String getHUMIDEX() {
            return HUMIDEX;
            }

    public double getRH2m() {
            return RH2m;
            }

    public double getPRMSL() {
            return PRMSL;
            }

    public double getAPCPsfc() {
            return APCPsfc;
            }

    public double getWNDSPD10m() {
            return WNDSPD10m;
            }

    public double getWNDGUST10m() {
            return WNDGUST10m;
            }

    public double getWNDDIR10m() {
            return WNDDIR10m;
            }

    public String getWNDDIRCARD10() {
            return WNDDIRCARD10;
            }

    public double getISSNOW() {
            return ISSNOW;
            }

    public String getHCDC() {
            return HCDC;
            }

    public String getMCDC() {
            return MCDC;
            }

    public String getLCDC() {
            return LCDC;
            }

    public double getHGT0C() {
            return HGT0C;
            }

    public double getKINDEX() {
            return KINDEX;
            }

    public String getCAPE180_0() {
            return CAPE180_0;
            }

    public double getCIN180_0() {
            return CIN180_0;
            }

    // Setter Methods

    public void setICON(String ICON) {
            this.ICON = ICON;
            }

    public void setCONDITION(String CONDITION) {
            this.CONDITION = CONDITION;
            }

    public void setCONDITION_KEY(String CONDITION_KEY) {
            this.CONDITION_KEY = CONDITION_KEY;
            }

    public void setTMP2m(double TMP2m) {
            this.TMP2m = TMP2m;
            }

    public void setDPT2m(double DPT2m) {
            this.DPT2m = DPT2m;
            }

    public void setWNDCHILL2m(String WNDCHILL2m) {
            this.WNDCHILL2m = WNDCHILL2m;
            }

    public void setHUMIDEX(String HUMIDEX) {
            this.HUMIDEX = HUMIDEX;
            }

    public void setRH2m(double RH2m) {
            this.RH2m = RH2m;
            }

    public void setPRMSL(double PRMSL) {
            this.PRMSL = PRMSL;
            }

    public void setAPCPsfc(double APCPsfc) {
            this.APCPsfc = APCPsfc;
            }

    public void setWNDSPD10m(double WNDSPD10m) {
            this.WNDSPD10m = WNDSPD10m;
            }

    public void setWNDGUST10m(double WNDGUST10m) {
            this.WNDGUST10m = WNDGUST10m;
            }

    public void setWNDDIR10m(double WNDDIR10m) {
            this.WNDDIR10m = WNDDIR10m;
            }

    public void setWNDDIRCARD10(String WNDDIRCARD10) {
            this.WNDDIRCARD10 = WNDDIRCARD10;
            }

    public void setISSNOW(double ISSNOW) {
            this.ISSNOW = ISSNOW;
            }

    public void setHCDC(String HCDC) {
            this.HCDC = HCDC;
            }

    public void setMCDC(String MCDC) {
            this.MCDC = MCDC;
            }

    public void setLCDC(String LCDC) {
            this.LCDC = LCDC;
            }

    public void setHGT0C(double HGT0C) {
            this.HGT0C = HGT0C;
            }

    public void setKINDEX(double KINDEX) {
            this.KINDEX = KINDEX;
            }

    public void setCAPE180_0(String CAPE180_0) {
            this.CAPE180_0 = CAPE180_0;
            }

    public void setCIN180_0(double CIN180_0) {
            this.CIN180_0 = CIN180_0;
            }
}