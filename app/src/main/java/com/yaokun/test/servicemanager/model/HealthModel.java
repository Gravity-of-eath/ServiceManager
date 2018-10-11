package com.yaokun.test.servicemanager.model;

import android.graphics.Color;

import com.yaokun.test.servicemanager.interfaces.AbsDataModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HealthModel {


    /**
     * result : [{"servcieName":"esale-cloud-dms","stat":"Sl","mem":"2.7","port":"8673","ip":"13.112.64.129","cpu":"15.8","pid":"23512","portStatus":"ok","time":"13:02","serviceId":null,"status":"UP"},{"servcieName":"esale-cloud-file","stat":"Sl","mem":"6.4","port":"8606","ip":"13.112.64.129","cpu":"17.3","pid":"23459","portStatus":"ok","time":"14:14","serviceId":null,"status":"UP"},{"servcieName":"esale-cloud-mbs","stat":"Sl","mem":"2.7","port":"8671","ip":"13.112.64.129","cpu":"18.9","pid":"23302","portStatus":"ok","time":"15:36","serviceId":null,"status":"UP"},{"servcieName":"esale-cloud-oms","stat":"Sl","mem":"3.1","port":"8674","ip":"13.112.64.129","cpu":"16.9","pid":"23112","portStatus":"ok","time":"13:59","serviceId":null,"status":"UP"},{"servcieName":"esale-cloud-pbs","stat":"Sl","mem":"5.0","port":"8672","ip":"13.112.64.129","cpu":"27.3","pid":"22602","portStatus":"ok","time":"22:33","serviceId":null,"status":"UP"},{"servcieName":"esale-cloud-service","stat":"Sl","mem":"4.5","port":"8605","ip":"13.112.64.129","cpu":"84.6","pid":"27773","portStatus":"ok","time":"2:58","serviceId":null,"status":"UP"},{"servcieName":"esale-cloud-ums","stat":null,"mem":null,"port":"8670","ip":"13.112.64.129","cpu":null,"pid":null,"portStatus":"no","time":null,"serviceId":null,"status":"error"},{"servcieName":"esale-cloud-dms","stat":"Sl","mem":"2.6","port":"8673","ip":"18.182.20.239","cpu":"16.8","pid":"25106","portStatus":"ok","time":"13:48","serviceId":null,"status":"UP"},{"servcieName":"esale-cloud-file","stat":"Sl","mem":"5.9","port":"8606","ip":"18.182.20.239","cpu":"17.2","pid":"25028","portStatus":"ok","time":"14:11","serviceId":null,"status":"UP"},{"servcieName":"esale-cloud-mbs","stat":"Sl","mem":"2.7","port":"8671","ip":"18.182.20.239","cpu":"19.3","pid":"24894","portStatus":"ok","time":"15:51","serviceId":null,"status":"UP"},{"servcieName":"esale-cloud-oms","stat":"Sl","mem":"2.8","port":"8674","ip":"18.182.20.239","cpu":"16.6","pid":"24658","portStatus":"ok","time":"13:39","serviceId":null,"status":"UP"},{"servcieName":"esale-cloud-pbs","stat":"Sl","mem":"5.0","port":"8672","ip":"18.182.20.239","cpu":"28.0","pid":"24287","portStatus":"ok","time":"22:59","serviceId":null,"status":"UP"},{"servcieName":"esale-cloud-service","stat":"Sl","mem":"4.0","port":"8605","ip":"18.182.20.239","cpu":"106","pid":"8075","portStatus":"ok","time":"2:29","serviceId":null,"status":"UP"},{"servcieName":"esale-cloud-ums","stat":"Sl","mem":"2.8","port":"8670","ip":"18.182.20.239","cpu":"19.2","pid":"23988","portStatus":"ok","time":"15:46","serviceId":null,"status":"UP"},{"servcieName":"esale-cloud-dms","stat":"Sl","mem":"2.7","port":"8673","ip":"13.114.162.152","cpu":"15.2","pid":"1891","portStatus":"ok","time":"12:28","serviceId":null,"status":"UP"},{"servcieName":"esale-cloud-file","stat":"Sl","mem":"5.8","port":"8606","ip":"13.114.162.152","cpu":"15.6","pid":"1838","portStatus":"ok","time":"12:48","serviceId":null,"status":"UP"},{"servcieName":"esale-cloud-mbs","stat":"Sl","mem":"2.7","port":"8671","ip":"13.114.162.152","cpu":"17.8","pid":"1588","portStatus":"ok","time":"14:35","serviceId":null,"status":"UP"},{"servcieName":"esale-cloud-oms","stat":"Sl","mem":"2.8","port":"8674","ip":"13.114.162.152","cpu":"15.5","pid":"1160","portStatus":"ok","time":"12:44","serviceId":null,"status":"UP"},{"servcieName":"esale-cloud-pbs","stat":"Sl","mem":"5.0","port":"8672","ip":"13.114.162.152","cpu":"25.5","pid":"501","portStatus":"ok","time":"20:56","serviceId":null,"status":"UP"},{"servcieName":"esale-cloud-service","stat":"Sl","mem":"4.5","port":"8605","ip":"13.114.162.152","cpu":"180","pid":"4017","portStatus":"ok","time":"2:04","serviceId":null,"status":"UP"},{"servcieName":"esale-cloud-ums","stat":"Sl","mem":"2.9","port":"8670","ip":"13.114.162.152","cpu":"17.7","pid":"32245","portStatus":"ok","time":"14:33","serviceId":null,"status":"UP"},{"servcieName":"esale-cloud-dms","stat":"Sl","mem":"2.7","port":"8673","ip":"54.65.170.38","cpu":"15.7","pid":"16811","portStatus":"ok","time":"12:50","serviceId":null,"status":"UP"},{"servcieName":"esale-cloud-file","stat":"Sl","mem":"6.5","port":"8606","ip":"54.65.170.38","cpu":"15.9","pid":"16767","portStatus":"ok","time":"12:59","serviceId":null,"status":"UP"},{"servcieName":"esale-cloud-mbs","stat":"Sl","mem":"2.7","port":"8671","ip":"54.65.170.38","cpu":"18.0","pid":"16708","portStatus":"ok","time":"14:43","serviceId":null,"status":"UP"},{"servcieName":"esale-cloud-oms","stat":"Sl","mem":"3.1","port":"8674","ip":"54.65.170.38","cpu":"15.9","pid":"16646","portStatus":"ok","time":"13:02","serviceId":null,"status":"UP"},{"servcieName":"esale-cloud-pbs","stat":"Sl","mem":"5.0","port":"8672","ip":"54.65.170.38","cpu":"25.9","pid":"16452","portStatus":"ok","time":"21:11","serviceId":null,"status":"UP"},{"servcieName":"esale-cloud-service","stat":"Sl","mem":"6.0","port":"8605","ip":"54.65.170.38","cpu":"18.8","pid":"15434","portStatus":"ok","time":"15:23","serviceId":null,"status":"UP"},{"servcieName":"esale-cloud-ums","stat":"Sl","mem":"6.4","port":"8670","ip":"54.65.170.38","cpu":"24.8","pid":"29690","portStatus":"ok","time":"12:35","serviceId":null,"status":"UP"}]
     * isSuccess : true
     */

    private boolean isSuccess;
    private List<ResultBean> result;

    public boolean isIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean implements AbsDataModel {
        /**
         * servcieName : esale-cloud-dms
         * stat : Sl
         * mem : 2.7
         * port : 8673
         * ip : 13.112.64.129
         * cpu : 15.8
         * pid : 23512
         * portStatus : ok
         * time : 13:02
         * serviceId : null
         * status : UP
         */

        private String servcieName;
        private String stat;
        private String mem;
        private String port;
        private String ip;
        private String cpu;
        private String pid;
        private String portStatus;
        private String time;
        private Object serviceId;
        private String status;

        public String getServcieName() {
            return servcieName;
        }

        public void setServcieName(String servcieName) {
            this.servcieName = servcieName;
        }

        public String getStat() {
            return stat;
        }

        public void setStat(String stat) {
            this.stat = stat;
        }

        public String getMem() {
            return mem;
        }

        public void setMem(String mem) {
            this.mem = mem;
        }

        public String getPort() {
            return port;
        }

        public void setPort(String port) {
            this.port = port;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getCpu() {
            return cpu;
        }

        public void setCpu(String cpu) {
            this.cpu = cpu;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getPortStatus() {
            return portStatus;
        }

        public void setPortStatus(String portStatus) {
            this.portStatus = portStatus;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public Object getServiceId() {
            return serviceId;
        }

        public void setServiceId(Object serviceId) {
            this.serviceId = serviceId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        @Override
        public int getColumnCount() {
            return 11;
        }

        @Override
        public int getCellColor(int pos) {
            int colo = Color.BLACK;
            switch (pos) {
                case 0:
                    colo = Color.GREEN;
                    break;
                case 1:
                    colo = Color.RED;
                    break;
                case 2:
                    colo = Color.BLACK;
                    break;
                case 3:
                    colo = Color.YELLOW;
                    break;
                case 4:
                    colo = Color.RED;
                    break;
                default:
                    colo = Color.BLACK;

            }
            return colo;
        }

        @Override
        public int getTextColor(int pos) {
            return 0;
        }

        @Override
        public List<String> getDatalist() {
            ArrayList<String> strings = new ArrayList<>();
            strings.add(servcieName);
            strings.add(cpu);
            strings.add(stat);
            strings.add(mem);
            strings.add(port);
            strings.add(ip);
            strings.add(pid);
            strings.add(portStatus);
            strings.add(time);
            strings.add(status);
            return strings;
        }
    }
}
