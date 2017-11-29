/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import java.util.List;
import java.util.Map;
import model.user.tracking.ChartData;
import model.user.tracking.FollowUsers;

/**
 *
 * @author Do Hung Cuong
 */
public interface UserDAO {
    public List<FollowUsers> getListFollowUsers();
    
    public Map getFollowUsersMap(List<FollowUsers> list);
    
    public Map getFollowUsersMapByIP(List<FollowUsers> list);
    
    public Map getFollowUsersMapByOneIP(List<FollowUsers> list, String ip);
    
    public Map getMapFollowUsersCountry(List<FollowUsers> list);
    
    public String getFollowUsersCountry(List<FollowUsers> list);
    
    public List<ChartData> getListFollowUsersChartData(List<FollowUsers> list);

    public Map getPageAccessChartData(List<FollowUsers> list);

    public Map getPageAccessChartDataByIP(String ipaddress, List<FollowUsers> list);
    
    public String getJSONPageAccess(Map m);
}
