package exceptions;

import network.Member;
import network.Service;

/**
 * Thrown when the Member has already the Service
 * */
public class AlreadyHasService extends Exception {
    private Member member;
    private Service service;

    public AlreadyHasService(String message, Member member, Service service){
        super(message);
        this.member = member;
        this.service = service;
    }

    public Member getMember() {
        return member;
    }

    public Service getService() {
        return service;
    }
}
