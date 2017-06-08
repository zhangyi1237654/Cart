package entity;
//购物车类

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

public class Cart {
	
	//购买商品的集合
	private HashMap<Items, Integer> goods;
	
	//购物车的总金额
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
	
	//添加商品进购物车的方法
	public boolean addGoodsInCart(Items item,int number){
		
		if(goods.containsKey(item)){
			goods.put(item, goods.get(item)+number);
		}else{
			goods.put(item, number);
		}
		calTotalPrice();//重新计算总金额
		return true;
	}
	//删除商品的方法
	public boolean removeGoodsFormCart(Items item){
		
		goods.remove(item);
		calTotalPrice();
		return true;
	}
	//统计商品的总金额
	public double calTotalPrice(){
		
		double sum = 0.0;
		Set<Items> keys = goods.keySet();//获得键的集合
		Iterator<Items> it = keys.iterator();//获得迭代器对象
		while(it.hasNext()){
			Items i = it.next();
			sum+=i.getPrice()*goods.get(i);
		}
		this.setTotalPrice(sum);//设置购物车的总金额
		return this.getTotalPrice();
	}
	
	//添加主方法测试
	public static void main(String[] args) {
		
		//先创建两个商品对象
		Items i1 = new Items(1,"沃特篮球鞋","温州",200,500,"001.jpg");
		Items i2 = new Items(2,"李宁运动鞋","广州",300,500,"002.jpg");
		Items i3 = new Items(1,"沃特篮球鞋","温州",200,500,"001.jpg");
		
		Cart c = new Cart();
		
		c.addGoodsInCart(i1, 1);
		c.addGoodsInCart(i2, 2);
		//再次购买
		c.addGoodsInCart(i3, 3);
		//遍历购物商品的集合
		Set<Map.Entry<Items, Integer>> items = c.getGoods().entrySet();
		for(Map.Entry<Items, Integer> obj : items){
			System.out.println(obj.toString());
		}
		System.out.println("购物车的总金额:"+c.totalPrice);
	}
}
