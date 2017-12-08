package com.hypodiabetic.happplus.helperObjects;

import com.hypodiabetic.happplus.MainApp;
import com.hypodiabetic.happplus.R;
import com.hypodiabetic.happplus.plugins.AbstractClasses.AbstractPluginBase;

import java.util.Date;

/**
 * Created by Tim on 25/12/2016.
 * Object detailing the status of a Device
 */

public class DeviceStatus {

    private boolean error;
    private boolean warning;
    private String comment;
    private Date captured;

    public DeviceStatus(){
        captured        =   new Date();
    }

    public String getStatusDisplay(){
        if (error){
            return MainApp.getInstance().getString(R.string.device_status_error);
        } else if (warning){
            return MainApp.getInstance().getString(R.string.device_status_warn);
        } else {
            return MainApp.getInstance().getString(R.string.device_status_ok);
        }
    }

    public boolean getIsUsable(){
        if (error){
            return  false;
        } else if (warning){
            return true;
        } else {
            return true;
        }
    }

    public String getComment(){
                        return comment;
    }

    public Date getDateCaptured(){
            return captured;
    }

    public void addComment(String comment){
        if (this.comment == null) {
            this.comment = comment;
        } else {
            this.comment = this.comment + ". " + comment;
        }
    }

    public void hasError(Boolean error){
        this.error  =   error;
    }

    public void hasWarning(Boolean warning){
        this.warning    =   warning;
    }

    public void checkPluginIDependOn(AbstractPluginBase plugin, String nameOfPlugin){
        if (plugin == null){
            error   =   true;
            addComment("'" + nameOfPlugin + "' " + MainApp.getInstance().getString(R.string.plugin_not_found));
        } else if (!plugin.getStatus().getIsUsable()){
            error   =   true;
            addComment("'" + plugin.getPluginDisplayName() + "' " + MainApp.getInstance().getString(R.string.plugin_has_errors) + ": " + plugin.getStatus().getComment());
        }
    }
}
