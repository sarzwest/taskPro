package org.jboss.tools.example.service;

import org.jboss.ejb3.annotation.SecurityDomain;
import org.jboss.tools.example.model.Member;

import javax.annotation.security.DeclareRoles;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.logging.Logger;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
@DeclareRoles({"admin", "kantor", "student"})
@SecurityDomain("moje-domena")
public class MemberRegistration {

   @Inject
   private Logger log;

   @Inject
   private EntityManager em;

   @Inject
   private Event<Member> memberEventSrc;

   public void register(Member member) throws Exception {
      log.info("Registering " + member.getName());
      em.persist(member);
      memberEventSrc.fire(member);
   }
}
