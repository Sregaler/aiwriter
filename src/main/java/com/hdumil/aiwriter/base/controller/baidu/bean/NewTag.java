package com.hdumil.aiwriter.base.controller.baidu.bean;
import lombok.Data;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
@Data
@ConfigurationProperties(prefix = "NewTag")
public class NewTag {

    private List<HashMap<String,List<String>>> province;
    private List<HashMap<String,List<String>>> football_team;
    private List<HashMap<String,List<String>>> basketball_team;
    private HashMap<String,String> stock_code;
    private HashMap<String,String> symbol;
    private HashMap<String,String> bond_code;

    public String getProvinceJSON() {
        return new JSONArray(province).toString();
    }
    public String getFootballTeamJSON() {
        return new JSONArray(football_team).toString();
    }
    public String getBasketballTeamJSON() {
        return new JSONArray(basketball_team).toString();
    }
    public String getStockCodeJSON() {
        return new JSONObject(stock_code).toString();
    }
    public String getSymbolJSON() {
        return new JSONObject(symbol).toString();
    }
    public String getBondCodeJSON() {
        return new JSONObject(bond_code).toString();
    }

    public List<String> isInStockCode(List<String> list){
        List<String> res = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if(!stock_code.containsKey(list.get(i))) res.add(list.get(i));
        }
        return res;
    }
    public List<String> isInSymbol(List<String> list){
        List<String> res = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if(!symbol.containsKey(list.get(i))) res.add(list.get(i));
        }
        return res;
    }
    public List<String> isInBondCode(List<String> list){
        List<String> res = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if(!bond_code.containsKey(list.get(i))) res.add(list.get(i));
        }
        return res;
    }
}
