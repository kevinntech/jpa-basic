package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try{
            Member member = new Member();
            member.setUsername("member1");
            member.setHomdAddress(new Address("city1","street", "1000" ));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("피자");

            member.getAddressHistory().add(new AddressEntity("old1","street", "1000" ));
            member.getAddressHistory().add(new AddressEntity("old2","street", "1000" ));

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("================ START ================");
            Member findMember = em.find(Member.class, member.getId());
//
//            Address oldAddress = findMember.getHomdAddress();
//
//            // 1. 임베디드 값 타입 수정
//            findMember.setHomdAddress(new Address("newCity",
//                                                    oldAddress.getStreet(),
//                                                    oldAddress.getZipcode()));
//
//            // 2. 기본값 타입 컬렉션 수정
//            findMember.getFavoriteFoods().remove("치킨");
//            findMember.getFavoriteFoods().add("한식");
//
//            // 3. 임베디드 값 타입 컬렉션 수정
//            List<AddressEntity> addressHistory = findMember.getAddressHistory();
//            addressHistory.remove(new AddressEntity("old1","street", "1000" ));
//            addressHistory.add(new AddressEntity("newCity1","street", "1000" ));



            tx.commit();
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }

        emf.close(); // 전체 애플리케이션이 끝나면 EntityManagerFactory도 닫는다.
    }

}
