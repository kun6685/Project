package project.control;

import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

import project.menu.BurgerDAO;
import project.menu.BurgerVO;
import project.menu.DrinkDAO;
import project.menu.DrinkVO;
import project.menu.SideDAO;
import project.menu.SideVO;
import project.order.OrderDAO;
import project.order.OrderVO;
import project.user.UserDAO;
import project.user.UserVO;

public class ProjectControl {
	  
    Scanner scanner = new Scanner(System.in);
    UserDAO userDAO = new UserDAO();
    
    BurgerDAO burgerDAO = new BurgerDAO();
    SideDAO sideDAO = new SideDAO();
    DrinkDAO drinkDAO = new DrinkDAO();

    BurgerVO burgerVO = new BurgerVO();
    SideVO sideVO = new SideVO();
    DrinkVO drinkVO = new DrinkVO();
    OrderVO orderVO;
    
    String userId;
    
    boolean isTrue = true;
    
    private OrderDAO orderDAO;

    public ProjectControl() {
        this.orderDAO = new OrderDAO();
    }

    public void main() {

        while (isTrue) {
        	System.out.println("======================================================");
        	System.out.println("                버거킹에 오신 것을 환영합니다!!                ");
        	System.out.println("======================================================");
        	System.out.println("    1.로그인     2.회원가입     3.회원정보 찾기     4.종료    ");
        	System.out.println("------------------------------------------------------");
        	  System.out.println("");
            System.out.print("선택 >> ");
            int menuSelect = scanner.nextInt();
            scanner.nextLine(); // Enter 키 제거

            switch (menuSelect) {
                case 1:
                	  System.out.println("");
                    login(); // 로그인 화면 이동
                    break;
                case 2:
                	  System.out.println("");
                    register(); // 회원가입 화면 이동
                    break;
                case 3:
                	  System.out.println("");
                    findUserInfo(); // 회원정보 찾기 화면 이동
                    break;
                case 4:
                	System.out.println("");
                	System.out.println("------------------------------------------------------");
                	System.out.println("                      종료되었습니다.                      ");
                	System.out.println("======================================================");
                    isTrue = false;
                    break;
            }
        }
    }
    
    // 버거주문 화면
    void bugerOrder() {
        while (true) {
            System.out.println("======================================================");
            System.out.println("                       버거 선택                       ");
            System.out.println("------------------------------------------------------");
            System.out.println("               1.롱치킨버거     2.불고기와퍼               ");
            System.out.println("------------------------------------------------------");

            System.out.println("q.돌아가기");
            System.out.println("");

            System.out.print("선택 >> ");
            String input = scanner.nextLine();
            if ("q".equalsIgnoreCase(input)) {
                break;
            }
            
            int menuSelect;
            try {
                menuSelect = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("잘못된 입력입니다. 숫자를 입력하거나 'q'를 입력하여 돌아가세요.");
                continue;
            }
            
            switch(menuSelect) {
            	case 1:
            	case 2:
            	  SideOrder(menuSelect); // 사이드 메뉴 선택 화면으로 이동
                  break;
            	default:
                  System.out.println("잘못된 입력입니다. 1 또는 2를 입력하거나 'q'를 입력하여 돌아가세요.");
                  break;
            }
        }
    }
    
    void SideOrder(int burgerMenu) {
    	 while (true) {
             System.out.println("======================================================");
             System.out.println("                      사이드 선택                       ");
             System.out.println("------------------------------------------------------");
             System.out.println("                1.감자튀김     2.치즈스틱                ");
             System.out.println("------------------------------------------------------");

             System.out.println("q.돌아가기");
             System.out.println("");

             System.out.print("선택 >> ");
             String input = scanner.nextLine();
             if ("q".equalsIgnoreCase(input)) {
                 break;
             }
             
             int menuSelect;
             try {
                 menuSelect = Integer.parseInt(input);
             } catch (NumberFormatException e) {
                 System.out.println("잘못된 입력입니다. 숫자를 입력하거나 'q'를 입력하여 돌아가세요.");
                 continue;
             }
             
             switch(menuSelect) {
             	case 1:
             	case 2:
             	     drinkOrder(burgerMenu, menuSelect); // 사이드 메뉴 선택 화면으로 이동
                   break;
             	default:
                   System.out.println("잘못된 입력입니다. 1 또는 2를 입력하거나 'q'를 입력하여 돌아가세요.");
                   break;
             }
         }
    }
    
    // 음료수 화면
    void drinkOrder(int burgerMenu, int sideMenu) {
    	while (true) {
            System.out.println("======================================================");
            System.out.println("                      음료수 선택                       ");
            System.out.println("------------------------------------------------------");
            System.out.println("                  1.콜라     2.사이다                  ");
            System.out.println("------------------------------------------------------");

            System.out.println("q.돌아가기");
            System.out.println("");

            System.out.print("선택 >> ");
            String input = scanner.nextLine();
            if ("q".equalsIgnoreCase(input)) {
                break;
            }
            
            int menuSelect;
            try {
                menuSelect = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("잘못된 입력입니다. 숫자를 입력하거나 'q'를 입력하여 돌아가세요.");
                continue;
            }
            
            switch(menuSelect) {
            	case 1:
            	case 2:
            	  	confirmOrder(burgerMenu, sideMenu, menuSelect); // 주문확인 화면으로 이동
                  break;
            	default:
                  System.out.println("잘못된 입력입니다. 1 또는 2를 입력하거나 'q'를 입력하여 돌아가세요.");
                  break;
            }
        }
    }
    
    void confirmOrder(int burgerMenu, int sideMenu, int drinkMenu) {
    	
    	String burgerMenuId = Integer.toString(burgerMenu);
    	String sideMenuId = Integer.toString(sideMenu);
    	String drinkMenuId = Integer.toString(drinkMenu);
    	
    	burgerVO.setMenuId(burgerMenuId);
    	sideVO.setMenuId(sideMenuId);
    	drinkVO.setMenuId(drinkMenuId);
    	
    	
    	String burgerMenuName = burgerDAO.getBurgerName(burgerVO.getMenuId());
    	String sideMenuName = sideDAO.getSideName(sideVO.getMenuId());
    	String drinkMenuName = drinkDAO.getDrinkName(drinkVO.getMenuId());
        
      int burgerMenuPrice = burgerDAO.getBurgerPrice(burgerVO.getMenuId());
      int sideMenuPrice = sideDAO.getSidePrice(sideVO.getMenuId());
      int drinkMenuPrice = drinkDAO.getDrinkPrice(drinkVO.getMenuId());
      
      int totalOrderPrice = burgerMenuPrice + sideMenuPrice + drinkMenuPrice;
      
      boolean isTrue = true;
        
        while(isTrue) {
	        System.out.println("======================================================");
	        System.out.println("                     주문내역 확인                      ");
	        System.out.println("------------------------------------------------------");
	        System.out.println("버거 : " + burgerMenuName);
	        System.out.println("사이드 : " + sideMenuName);
	        System.out.println("음료 : " + drinkMenuName);
	        System.out.println("총 금액 : " + totalOrderPrice);
	        System.out.println("주문하신 메뉴가 맞습니까?(Y/N)");
	        
	        String menuSelect = scanner.nextLine();
	        if(menuSelect.equals("Y") || menuSelect.equals("y")) {
	        	System.out.println("======================================================");
		        System.out.println("                       주문완료                        ");
		        System.out.println("------------------------------------------------------");
		        orderDAO.orderVerification(userId, 
		        burgerMenuName, burgerMenuPrice,
            sideMenuName, sideMenuPrice,
            drinkMenuName, drinkMenuPrice, totalOrderPrice);
		        isTrue = false;
		        Timestamp orderTime = new Timestamp(System.currentTimeMillis());
		        orderVO =  new OrderVO(userId, 
				    burgerMenuName, burgerMenuPrice,
		        sideMenuName, sideMenuPrice,
		        drinkMenuName, drinkMenuPrice, totalOrderPrice, orderTime);
		        loginMainPage(userId);
	        } else {
	        	System.out.println("======================================================");
		        System.out.println("                       주문취소                        ");
		        System.out.println("------------------------------------------------------");
		        isTrue = false;
		        loginMainPage(userId);
	        }
        }
    }

    // 로그인 화면
    void login() {
        while (true) {
            System.out.println("======================================================");
            System.out.println("                        로그인                        ");
            System.out.println("------------------------------------------------------");

            System.out.println("q.돌아가기");
            System.out.println("");

            System.out.print("아이디 입력 > ");
            String userId = scanner.nextLine();
            if ("q".equalsIgnoreCase(userId)) {
                break;
            }

            System.out.print("패스워드 입력 > ");
            String userPassword = scanner.nextLine();
            if ("q".equalsIgnoreCase(userPassword)) {
                break;
            }

            UserVO userVO = new UserVO();
            userVO.setUserId(userId);
            this.userId = userId; 
            userVO.setUserPassword(userPassword);

            if (userDAO.loginCheck(userVO)) {
            		loginMainPage(userVO.getUserId());
                break;
            } else {
                System.out.println("");
                System.out.println("- 로그인 실패 -");
                System.out.println("");
            }

        }
    }
    
    // 로그인시 페이지
    void loginMainPage(String userId) {
    	
    	  String userName = userDAO.getUserName(userId);
    	  boolean isTrue = true;
    	
        while (isTrue) {
        	  System.out.println("");
            System.out.println("======================================================");
            System.out.println("           " + userName + "님 버거킹에 오신것을 환영합니다!!      ");
            System.out.println("======================================================");
            System.out.println("   1.주문하기     2.구매내역     3.회원탈퇴     4.로그아웃   ");
            System.out.println("------------------------------------------------------");

            System.out.print("선택 >> ");
            int menuSelect = scanner.nextInt();
            scanner.nextLine(); // Enter 키 제거

            switch (menuSelect) {
                case 1:
                	bugerOrder(); // 버거 주문하기 화면 이동
                  break;
                case 2:
                	orderHistory(userId); // 구매내역 화면 이동
                	isTrue = false;
                  break;
                case 3:
                	deleteUser(); // 회원탈퇴 화면 이동
                	isTrue = false;
                  break;  
                case 4:
                	main();
                	isTrue = false;
                  break;  
            }
        }
    }

    // 회원가입 화면
    void register() {
        while (true) {
            System.out.println("======================================================");
            System.out.println("                       회원가입                       ");
            System.out.println("------------------------------------------------------");

            System.out.println("q.돌아가기");
            System.out.println("");

            System.out.print("이름 입력 > ");
            String userName = scanner.nextLine();
            if ("q".equalsIgnoreCase(userName)) {
                break;
            }

            System.out.print("아이디 입력 > ");
            String userId = scanner.nextLine();
            if ("q".equalsIgnoreCase(userId)) {
                break;
            }

            System.out.print("패스워드 입력 > ");
            String userPassword = scanner.nextLine();
            if ("q".equalsIgnoreCase(userPassword)) {
                break;
            }

            System.out.print("전화번호 입력 > ");
            String phoneNumber = scanner.nextLine();
            if ("q".equalsIgnoreCase(phoneNumber)) {
                break;
            }

            UserVO userVO = new UserVO();
            userVO.setUserName(userName);
            userVO.setUserId(userId);
            userVO.setUserPassword(userPassword);
            userVO.setPhoneNumber(phoneNumber);

            if (userDAO.registerCheck(userVO)) {
                System.out.println("");
                System.out.println("- 회원가입 완료 -");
                System.out.println("");
                break;
            } else {
                System.out.println("");
                System.out.println("- 회원가입 실패 -");
                System.out.println("");
            }
        }
    }

    // 회원정보 찾기 화면
    void findUserInfo() {
        while (true) {
            System.out.println("======================================================");
            System.out.println("                     회원정보 찾기                      ");
            System.out.println("------------------------------------------------------");
            System.out.println("             1.아이디 찾기     2.비밀번호 찾기             ");
            System.out.println("------------------------------------------------------");
            System.out.println("q.돌아가기");
            System.out.println("");

            System.out.print("선택 >> ");
            String input = scanner.nextLine().trim();

            if ("q".equalsIgnoreCase(input)) {
                break;
            }

            int menuSelect;
            try {
                menuSelect = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("잘못된 입력입니다. 숫자를 입력하거나 'q'를 입력하여 돌아가세요.");
                continue;
            }

            switch (menuSelect) {
                case 1:
                    System.out.println("======================================================");
                    System.out.println("                      아이디 찾기                       ");
                    System.out.println("------------------------------------------------------");
                    System.out.println("q.돌아가기");
                    System.out.println("");

                    System.out.print("이름 입력 > ");
                    String userName = scanner.nextLine().trim();
                    if ("q".equalsIgnoreCase(userName)) {
                        break;
                    }

                    System.out.print("핸드폰 번호 입력 > ");
                    String phoneNumber = scanner.nextLine().trim();
                    if ("q".equalsIgnoreCase(phoneNumber)) {
                        break;
                    }

                    UserVO userVO = new UserVO();
                    userVO.setUserName(userName);
                    userVO.setPhoneNumber(phoneNumber);

                    if (userDAO.findUserId(userVO)) {
                        System.out.println("");
                        System.out.println("- 아이디 찾기 성공 -");
                        System.out.println("");
                    } else {
                        System.out.println("");
                        System.out.println("- 아이디 찾기 실패 -");
                        System.out.println("");
                    }
                    break;

                case 2:
                    System.out.println("======================================================");
                    System.out.println("                     비밀번호 찾기                      ");
                    System.out.println("------------------------------------------------------");
                    System.out.println("q.돌아가기");
                    System.out.println("");

                    System.out.print("이름 입력 > ");
                    userName = scanner.nextLine().trim();
                    if ("q".equalsIgnoreCase(userName)) {
                        break;
                    }

                    System.out.print("아이디 입력 > ");
                    String userId = scanner.nextLine().trim();
                    if ("q".equalsIgnoreCase(userId)) {
                        break;
                    }

                    System.out.print("핸드폰 번호 입력 > ");
                    phoneNumber = scanner.nextLine().trim();
                    if ("q".equalsIgnoreCase(phoneNumber)) {
                        break;
                    }

                    userVO = new UserVO();
                    userVO.setUserId(userId);
                    userVO.setUserName(userName);
                    userVO.setPhoneNumber(phoneNumber);

                    if (userDAO.findUserPassword(userVO)) {
                        System.out.println("");
                        System.out.println("- 비밀번호 찾기 성공 -");
                        System.out.println("");
                    } else {
                        System.out.println("");
                        System.out.println("- 비밀번호 찾기 실패 -");
                        System.out.println("");
                    }
                    break;

                default:
                    System.out.println("없는 메뉴입니다. 다시 선택해주세요.");
                    break;
            }
        }
    }
    
    void orderHistory(String userId) {
    	int sum = 0;
    	List<OrderVO> orderHistoryList = orderDAO.getOrderHistory(userId);
    	while(isTrue) {
	    	System.out.println("======================================================");
	      System.out.println("                       구매내역                        ");
	      System.out.println("------------------------------------------------------");
	      
	      for (OrderVO order : orderHistoryList) {
	        System.out.println("주문 번호: " + order.getOrderId());
	        System.out.println("주문 일시: " + order.getOrderTime());
	        System.out.println("버거: " + order.getBurgerMenuName() + "(" + order.getBurgerMenuPrice() + "원" + ")");
	        System.out.println("사이드: " + order.getSideMenuName() + "(" + order.getSideMenuPrice() + "원" + ")");
	        System.out.println("음료: " + order.getDrinkMenuName() + "(" + order.getDrinkMenuPrice() + "원" + ")");
	        System.out.println("총 가격: " + order.getTotalPrice() + "원");
	        System.out.println("------------------------------------------------------");
	        sum += order.getTotalPrice();
	        System.out.println("총 소비 금액 : " + sum + "원");
        }
        System.out.println("");
        System.out.println("q.돌아가기");
	      System.out.println("");
	      System.out.print("선택 >> ");
	      String input = scanner.nextLine();
	      if ("q".equalsIgnoreCase(input)) {
	      	loginMainPage(userId);
	      	break;
	      }
      }
    }
    
    void deleteUser() {
      while (true) {
          System.out.println("======================================================");
          System.out.println("                        회원탈퇴                        ");
          System.out.println("======================================================");
          System.out.println("");
          System.out.println("q.돌아가기");
          System.out.println("");
          
          System.out.print("이름 >> ");
          String userName = scanner.nextLine();
          if ("q".equalsIgnoreCase(userName)) {
              loginMainPage(userId);
              break;
          }
          
          System.out.print("아이디 >> ");
          String userId = scanner.nextLine();
          if ("q".equalsIgnoreCase(userId)) {
              loginMainPage(userId);
              break;
          }
          
          System.out.print("비밀번호 >> ");
          String userPassword = scanner.nextLine();
          if ("q".equalsIgnoreCase(userPassword)) {
              loginMainPage(userId);
              break;
          }
          
          boolean deletionSuccessful = userDAO.killUser(userName, userId, userPassword);
          if (deletionSuccessful) {
              main(); // 메인 메뉴로 돌아가기
              break;
          } else {
              System.out.println("회원 탈퇴에 실패하였습니다. 입력하신 정보를 다시 확인해주세요.");
          }
      }
   }
}
