package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import util.DBHelper;

import entity.Items;

//��Ʒ��ҵ���߼���
public class ItemsDAO {

	// ������е���Ʒ��Ϣ
	public ArrayList<Items> getAllItems() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Items> list = new ArrayList<Items>(); // ��Ʒ����
		try {
			conn = DBHelper.getConnection();
			String sql = "select * from items;"; // SQL���
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Items item = new Items();
				item.setId(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setCity(rs.getString("city"));
				item.setNumber(rs.getInt("number"));
				item.setPrice(rs.getInt("price"));
				item.setPicture(rs.getString("picture"));
				list.add(item);// ��һ����Ʒ���뼯��
			}
			return list; // ���ؼ��ϡ�
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			// �ͷ����ݼ�����
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			// �ͷ�������
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}

	}

	// ������Ʒ��Ż����Ʒ����
	public Items getItemsById(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBHelper.getConnection();
			String sql = "select * from items where id=?;"; // SQL���
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				Items item = new Items();
				item.setId(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setCity(rs.getString("city"));
				item.setNumber(rs.getInt("number"));
				item.setPrice(rs.getInt("price"));
				item.setPicture(rs.getString("picture"));
				return item;
			} else {
				return null;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			// �ͷ����ݼ�����
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			// �ͷ�������
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

		}
	}
	//��ȡ��������ǰ������Ʒ��Ϣ
	public ArrayList<Items> getViewList(String list)
	{
		Items item;
		System.out.println("list:"+list);
		ArrayList<Items> itemlist = new ArrayList<Items>();
		//int iCount=5; //ÿ�η���ǰ������¼
		if(list!=null&&list.length()>0)
		{
		    String[] arr = list.split(",");
		    System.out.println("arr.length="+arr.length);
		    //�����Ʒ��¼���ڵ���5��
		    for (int i = arr.length - 1; i >= 0; i--) {

				// ������Ʒ��ӵ�itemList��
				item = getItemsById(Integer.parseInt(arr[i]));

				if (itemlist.contains(item) || itemlist.size() >= 5) {

					continue;

				} else {

					itemlist.add(item);

				}
			}
//		    if(arr.length>=5)
//		    {
//		       for(int i=arr.length-1;i>=arr.length-iCount;i--)
//		       {
//		    	  Items item = getItemsById(Integer.parseInt(arr[i]));
//		    	  itemlist.add(item);  
//		       }
//		    }
//		    else
//		    {
//		    	for(int i=arr.length-1;i>=0;i--)
//		    	{
//		    		Items item = getItemsById(Integer.parseInt(arr[i]));
//		    		itemlist.add(item);
//		    	}
//		    }
//		    return itemlist;
//		}
//		else
//		{
//			return null;
//		}
		
	}
		return itemlist;
//	// ����cookie ����������������Ʒ  
//    public ArrayList<Items> getHistoryView(String cookie) {  
//        ArrayList<Items> list = new ArrayList<Items>();  
//        String ids[] = cookie.split(",");  
//        int counts = 3;// ��������������¼  
//        if (ids != null && ids.length > 0) {  
//            for (int i = ids.length - 1; i >= 0 && i > ids.length - counts - 1; i--) {  
//                Items item = getItemsById(Integer.parseInt(ids[i]));  
//                /* 
//                 * �����жϼ������Ƿ���ڵ�ǰ��Ʒ ������� counts+1 ���ȡһ�Σ���֤list��������3������ ����Ӵ���Ʒ 
//                 */  
//                if (list.contains(item)) {  
//                    counts++;  
//                    continue;  
//                }  
//                list.add(item);  
//            }  
//        }  
//        return list;  
    }  

}
