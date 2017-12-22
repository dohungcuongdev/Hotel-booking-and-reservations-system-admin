/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import java.util.List;
import model.user.tracking.ExternalIP;
import model.user.tracking.FollowUsers;
import model.user.tracking.PageAccessData;

/**
 *
 * @author Do Hung Cuong
 */
public interface TrackingDAO {
	
    public List<FollowUsers> getListFollowUsers();
    public List<PageAccessData> getPageAccessChartData();
    public List<PageAccessData> getPageAccessChartDataByIP(String ipaddress);
    public List<PageAccessData> getPageAccessChartDataByUsername(String username);
    public ExternalIP getExternalIPDetails(String external_ip_address);
}
