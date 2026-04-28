import java.util.ArrayList;
import java.util.List;

public class Pedido implements PedidoSubject {

    private String id;
    private String status;
    private Cliente cliente;
    private List<PedidoObserver> observers;

    public Pedido(String id, String statusInicial, Cliente cliente) {
        this.id = id;
        this.status = statusInicial;
        this.cliente = cliente;
        this.observers = new ArrayList<>();
    }

    @Override
    public void adicionarObserver(PedidoObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removerObserver(PedidoObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notificarObservers() {
        for (PedidoObserver observer : observers) {
            observer.atualizar(this);
        }
    }

    public void atualizarStatus(String novoStatus) {
        if (novoStatus == null || novoStatus.trim().isEmpty()) {
            throw new IllegalArgumentException("O novo status não pode ser nulo ou vazio.");
        }
        this.status = novoStatus;
        notificarObservers();
    }

    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public Cliente getCliente() {
        return cliente;
    }
}
