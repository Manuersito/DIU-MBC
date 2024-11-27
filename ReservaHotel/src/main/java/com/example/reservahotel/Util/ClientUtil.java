    package com.example.reservahotel.Util;

    import com.example.reservahotel.Cliente;
    import com.example.reservahotel.Modelo.ClienteVO;

    import java.util.ArrayList;

    public class ClientUtil {
        public static ArrayList<Cliente> getClientes(ArrayList<ClienteVO> clienteVOList) {
            ArrayList<Cliente> clientes = new ArrayList<>();
            for (ClienteVO clienteVO : clienteVOList) {
                Cliente cliente = new Cliente();
                cliente.setDni(clienteVO.getDNI());
                cliente.setFirstName(clienteVO.getFirstName());
                cliente.setLastName(clienteVO.getLastName());
                cliente.setStreet(clienteVO.getStreet());
                cliente.setCity(clienteVO.getCity());
                cliente.setState(clienteVO.getState());

                clientes.add(cliente);
            }
            return clientes;
        }

        public static ArrayList<ClienteVO> getClientesVO(ArrayList<Cliente> clientes) {
            ArrayList<ClienteVO> clienteVOList = new ArrayList<>();
            for (Cliente cliente : clientes) {
                ClienteVO clienteVO = new ClienteVO(
                        cliente.getDni(),
                        cliente.getFirstName(),
                        cliente.getLastName(),
                        cliente.getStreet(),
                        cliente.getCity(),
                        cliente.getState()
                );
                clienteVOList.add(clienteVO);
            }
            return clienteVOList;
        }

        public static ClienteVO clienteToClienteVO(Cliente cliente) {
            return new ClienteVO(
                    cliente.getDni(),
                    cliente.getFirstName(),
                    cliente.getLastName(),
                    cliente.getStreet(),
                    cliente.getCity(),
                    cliente.getState()
            );
        }

        public static Cliente clienteVOToCliente(ClienteVO clienteVO) {
            Cliente cliente = new Cliente();
            cliente.setDni(clienteVO.getDNI());
            cliente.setFirstName(clienteVO.getFirstName());
            cliente.setLastName(clienteVO.getLastName());
            cliente.setStreet(clienteVO.getStreet());
            cliente.setCity(clienteVO.getCity());
            cliente.setState(clienteVO.getState());
            return cliente;
        }
    }
