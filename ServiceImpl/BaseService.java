package com.pms.ServiceImpl;
import com.pms.constants.AppConstants;
import com.pms.Model.BaseModel;
import java.sql.Timestamp;
import java.util.Random;

public class BaseService {

//    public String genId(){
//        return genId("");
//    }
//
//    public String genId(String key){
//
//        key = key.replace(" ", "");
//        key = key.replaceAll("[^a-zA-Z0-9]", "");
//        key = key.length() < AppConstants.ID_GEN_FIRST_CHAR_LEN ? (key + AppConstants.ID_GEN_PADDING).toLowerCase().substring(0, 3).trim() : key.toLowerCase().substring(0, 3).trim();
//        return
//                String.valueOf(System.currentTimeMillis())
//                        + "_"
//                        + String.valueOf(10000 + new Random().nextInt(90000))
//                        +"_"
//                        + key
//                ;
//
//    }
//
//    public Timestamp getDateAndTime() {
//        return new Timestamp(System.currentTimeMillis());
//    }
//    public String getValidUser() {
//        return '';
//    }
//
//    public void setModifier(BaseModel obj) {
//        obj.setCreatedBy(getValidUser());
//        obj.setCreatedAt(getDateAndTime());
//
//        obj.setUpdatedAt(getDateAndTime());
//        obj.setUpdatedBy(getValidUser());
//    }
//
//    public void setModifierEdit(BaseModel obj) {
//        obj.setUpdatedAt(getDateAndTime());
//        obj.setUpdatedBy(getValidUser());
//    }
}
