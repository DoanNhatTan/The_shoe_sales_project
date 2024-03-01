/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import QLCC.model.NguoiDung;


/**
 *
 * @author PC
 */
public class AUTH {
     public static NguoiDung user = null;
    public static void clear(){
         AUTH.user = null;
     }
     public static boolean isLogin(){
         return AUTH.user != null;
     }
      public static boolean isManager(){
          return AUTH.isLogin() && user.isChucVu();
      }
}
