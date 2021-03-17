package vn.dshop.entity;

public enum Status {
    ToPay(1), ToShip(2), ToReceive(3), Completed(4), Cancelled(5), ReturnRefund(6);
    private final int status;
    private Status(int status){
        this.status = status;
    }
    public int getStatus(){
        return status;
    }
}
