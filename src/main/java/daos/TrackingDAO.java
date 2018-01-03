/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import java.util.List;

import model.api.user.tracking.ExternalIP;
import model.api.user.tracking.FollowUsers;
import model.api.user.tracking.CountryChartData;
import model.api.user.tracking.PageAccessChartData;

/**
 *
 * @author Do Hung Cuong
 */
public interface TrackingDAO {
	
    public List<FollowUsers> getListFollowUsers();
    public List<PageAccessChartData> getPageAccessChartData();
    public List<PageAccessChartData> getPageAccessChartDataByIP(String ipaddress);
    public List<PageAccessChartData> getPageAccessChartDataByUsername(String username);
    public ExternalIP getExternalIPDetails(String external_ip_address);
    public List<CountryChartData> getCountryChartData();
}
