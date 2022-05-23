package com.hdumil.aiwriter.base.controller.baidu;
import com.hdumil.aiwriter.base.controller.baidu.bean.NewTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/baidu/newTag")
public class NewTagController {

    @Autowired
    private NewTag newTag;

    @RequestMapping("/getProvince")
    @ResponseBody
    public String getProvince(){
        return newTag.getProvinceJSON();
    }
    @RequestMapping("/getFootballTeam")
    @ResponseBody
    public String getFootballTeam(){
        return newTag.getFootballTeamJSON();
    }
    @RequestMapping("/getBasketballTeam")
    @ResponseBody
    public String getBasketballTeam(){
        return newTag.getBasketballTeamJSON();
    }
    @RequestMapping("/getStockCode")
    @ResponseBody
    public String getStockCode(){
        return newTag.getStockCodeJSON();
    }
    @RequestMapping("/getSymbol")
    @ResponseBody
    public String getSymbol(){
        return newTag.getSymbolJSON();
    }
    @RequestMapping("/getBondCode")
    @ResponseBody
    public String getBondCode(){
        return newTag.getBondCodeJSON();
    }
}
