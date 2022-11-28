/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import DomainModel.Sach;
import ViewModel.SachViewModel;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface SachService {
    List<SachViewModel> getAll();
    List<Sach> getAllSach();
    String inert(Sach s);
    String update(Sach s , String id);
    String delete(String id);
    List<SachViewModel> searchTen(String temp);
    Sach selectName01(String name);

}
