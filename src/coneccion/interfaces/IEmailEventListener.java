package coneccion.interfaces;

import coneccion.utils.Email;
import java.util.List;

/**
 *
 * @author  DAVID, ELMER
 */
public interface IEmailEventListener {
      void onReceiveEmailEvent(List<Email> emails);
}
