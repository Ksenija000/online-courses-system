package com.service;

import com.model.Admin;
import com.storage.Database;

public class AdminService {
    //поиск админа по имени
    public static Admin findAdminByName(String adminName ){
        for (Admin admin: Database.admins){
            if(admin.getName().equals(adminName)){
                return  admin;
            }
        }
        return null;
    }
}
