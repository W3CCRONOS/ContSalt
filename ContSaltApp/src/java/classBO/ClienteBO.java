/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classBO;

import classDAO.ClienteDAO;
import classTO.ClienteTO;
import java.util.List;

/**
 *
 * @author Almir
 */
public class ClienteBO {
    ClienteDAO cDAO;

    public ClienteBO() 
    {
        cDAO = new ClienteDAO();
    }

    public List<ClienteTO> getCLientes()
    {
        return cDAO.getClientes();
    }
}
