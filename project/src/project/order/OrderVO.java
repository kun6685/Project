package project.order;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class OrderVO {
	  private String isAdmin;
    private int orderId;
    private String userId;
    private String burgerMenuName;
    private int burgerMenuPrice;
    private String sideMenuName;
    private int sideMenuPrice;
    private String drinkMenuName;
    private int drinkMenuPrice;
    private int totalPrice;
    private Timestamp orderTime;

    // 생성자
    public OrderVO(String userId, String burgerMenuName, int burgerMenuPrice,
                   String sideMenuName, int sideMenuPrice, String drinkMenuName, int drinkMenuPrice,
                   int totalPrice, Timestamp orderTime) {
        this.userId = userId;
        this.burgerMenuName = burgerMenuName;
        this.burgerMenuPrice = burgerMenuPrice;
        this.sideMenuName = sideMenuName;
        this.sideMenuPrice = sideMenuPrice;
        this.drinkMenuName = drinkMenuName;
        this.drinkMenuPrice = drinkMenuPrice;
        this.totalPrice = totalPrice;
        this.orderTime = orderTime;
    }

    // Getter 및 Setter 메서드
    
    public String getIsAdmin() {
      return isAdmin;
  } 

    public void setIsAdmin(String isAdmin) {
      this.isAdmin = isAdmin;
    }
    
    
    
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBurgerMenuName() {
        return burgerMenuName;
    }

    public void setBurgerMenuName(String burgerMenuName) {
        this.burgerMenuName = burgerMenuName;
    }

    public int getBurgerMenuPrice() {
        return burgerMenuPrice;
    }

    public void setBurgerMenuPrice(int burgerMenuPrice) {
        this.burgerMenuPrice = burgerMenuPrice;
    }

    public String getSideMenuName() {
        return sideMenuName;
    }

    public void setSideMenuName(String sideMenuName) {
        this.sideMenuName = sideMenuName;
    }

    public int getSideMenuPrice() {
        return sideMenuPrice;
    }

    public void setSideMenuPrice(int sideMenuPrice) {
        this.sideMenuPrice = sideMenuPrice;
    }

    public String getDrinkMenuName() {
        return drinkMenuName;
    }

    public void setDrinkMenuName(String drinkMenuName) {
        this.drinkMenuName = drinkMenuName;
    }

    public int getDrinkMenuPrice() {
        return drinkMenuPrice;
    }

    public void setDrinkMenuPrice(int drinkMenuPrice) {
        this.drinkMenuPrice = drinkMenuPrice;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrderTime() {
    	  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH:mm");
        return dateFormat.format(orderTime);
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }

    // toString() 메서드 오버라이드 (디버깅 시 유용)
    @Override
    public String toString() {
        return "OrderVO{" +
                "orderId=" + orderId +
                ", userId='" + userId + '\'' +
                ", burgerMenuName='" + burgerMenuName + '\'' +
                ", burgerMenuPrice=" + burgerMenuPrice +
                ", sideMenuName='" + sideMenuName + '\'' +
                ", sideMenuPrice=" + sideMenuPrice +
                ", drinkMenuName='" + drinkMenuName + '\'' +
                ", drinkMenuPrice=" + drinkMenuPrice +
                ", totalPrice=" + totalPrice +
                ", orderTime=" + orderTime +
                '}';
    }
}
