package entity;
//���ﳵ��

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

public class Cart {
	
	//������Ʒ�ļ���
	private HashMap<Items, Integer> goods;
	
	//���ﳵ���ܽ��
	private double totalPrice;

	public HashMap<Items, Integer> getGoods() {
		return goods;
	}

	public void setGoods(HashMap<Items, Integer> goods) {
		this.goods = goods;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Cart() {
		goods = new HashMap<Items, Integer>();
		totalPrice = 0.0;
	}
	
	//�����Ʒ�����ﳵ�ķ���
	public boolean addGoodsInCart(Items item,int number){
		
		if(goods.containsKey(item)){
			goods.put(item, goods.get(item)+number);
		}else{
			goods.put(item, number);
		}
		calTotalPrice();//���¼����ܽ��
		return true;
	}
	//ɾ����Ʒ�ķ���
	public boolean removeGoodsFormCart(Items item){
		
		goods.remove(item);
		calTotalPrice();
		return true;
	}
	//ͳ����Ʒ���ܽ��
	public double calTotalPrice(){
		
		double sum = 0.0;
		Set<Items> keys = goods.keySet();//��ü��ļ���
		Iterator<Items> it = keys.iterator();//��õ���������
		while(it.hasNext()){
			Items i = it.next();
			sum+=i.getPrice()*goods.get(i);
		}
		this.setTotalPrice(sum);//���ù��ﳵ���ܽ��
		return this.getTotalPrice();
	}
	
	//�������������
	public static void main(String[] args) {
		
		//�ȴ���������Ʒ����
		Items i1 = new Items(1,"��������Ь","����",200,500,"001.jpg");
		Items i2 = new Items(2,"�����˶�Ь","����",300,500,"002.jpg");
		Items i3 = new Items(1,"��������Ь","����",200,500,"001.jpg");
		
		Cart c = new Cart();
		
		c.addGoodsInCart(i1, 1);
		c.addGoodsInCart(i2, 2);
		//�ٴι���
		c.addGoodsInCart(i3, 3);
		//����������Ʒ�ļ���
		Set<Map.Entry<Items, Integer>> items = c.getGoods().entrySet();
		for(Map.Entry<Items, Integer> obj : items){
			System.out.println(obj.toString());
		}
		System.out.println("���ﳵ���ܽ��:"+c.totalPrice);
	}
}
