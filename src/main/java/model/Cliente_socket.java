package model;
import model.Pedido;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import view.Pagina_pagamento;

public class Cliente_socket {

    private ArrayList<Pedido> pedidos;
    private ArrayList<Informacoes> pedidofinal;
    private String preco;
    private boolean conexaoEstabelecida = false;


    public Cliente_socket() {
        pedidos = new ArrayList<Pedido>();
        pedidofinal = new ArrayList<Informacoes>();
    }

    public Cliente_socket(ArrayList<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Cliente_socket(ArrayList<Informacoes> informacoesList, boolean dummy) {
        this.pedidofinal = informacoesList;
        
    }
    
    public void solicitapreco() {
        try {
            // Cria uma conexão socket com o servidor
             Socket socket = new Socket();
             socket.connect(new InetSocketAddress("localhost", 4444), 3000);

                // Cria Fluxo de saída
                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

                // Envia o ArrayList de pedidos através do ObjectOutputStream

                outputStream.writeObject(pedidos);
                outputStream.flush();

                // Cria fluxo de entrada
                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

                // Recebe a String do valor total enviado pelo servidor
        
                try {
                    preco = (String) inputStream.readObject();
                    Pagina_pagamento precoInfo = new Pagina_pagamento();
                    System.out.println("Valor total recebido: " + preco);
                    
                } catch (ClassNotFoundException e) {
                    System.out.println("Erro ao ler objeto do servidor: " + e.getMessage());
                }

                // Fecha o fluxo de entrada
                inputStream.close();
                outputStream.close();
                socket.close();
                System.out.println("Processo de preço finalizado");
                conexaoEstabelecida = true;
            

        } catch (SocketTimeoutException e) {
           JOptionPane.showMessageDialog(null, "Não foi possível conectar ao servidor. Verifique a conexão de rede.", "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Não foi possível conectar ao servidor. Verifique a conexão de rede.", "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
        }
     
        System.out.println("Fim do solicita preço");
 
    }
    
        public void enviapedido() {
        try {
            // Cria uma conexão socket com o servidor
             Socket socket = new Socket();
             socket.connect(new InetSocketAddress("localhost", 4445), 3000);

                // Cria Fluxo de saída
                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

                // Envia o ArrayList de pedidos através do ObjectOutputStream

                outputStream.writeObject(pedidofinal);
                outputStream.flush();       
             
                outputStream.close();
                socket.close();
                System.out.println("Processo de pedido finalizado");
            

        } catch (SocketTimeoutException e) {
           JOptionPane.showMessageDialog(null, "Não foi possível conectar ao servidor. Verifique a conexão de rede.", "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Não foi possível conectar ao servidor. Verifique a conexão de rede.", "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
        }
     
        System.out.println("Fim do processo");
 
    }
        

    public String getPreco() {
        return preco;
    }
    
    public boolean isConexaoEstabelecida() {
        return conexaoEstabelecida;
    }

}

    




