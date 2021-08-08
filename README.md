ÜZLETI CÉL/IGÉNY:

Időpontfoglaló alkalmazás sportpályákhoz.

ENTITÁSOK:

Court

    Név
    Nyitási idő
    Zárási idő
    Pályatípusa (Enum: squash. tennis, basketball, volleyball)
   
    Az entitás listázható, mindegyik attribútum módosítható, és az egész entitás törölhető
    Az /api/courts végponton
    
Customer

   Név
   Email
   Telefonszám
   
   Az entitás listázható, mindegyik attribútum módosítható, és az egész entitás törölhető
   Az /api/customers végponton
   
Reservation

   Kezdési idő
   Befejezési idő
   Pálya azonosítója
   Ügyfél azonositója
   
   Az entitás listázható, mindegyik attribútum módosítható, és az egész entitás törölhető
   A /api/reservations végponton
   
KAPCSOLATOK:

Egy foglaláshoz egy ügyfél tartozhat, de egy ügyfélhez több foglalás is tartozhat
Egy pályához több foglalás tartozhat, de egy foglaláshoz csak egypálya tartozhat

EGYÉB:
Az alkalmazás tartalmaz validációkat több attribútumra is.
Az adattároló réteg egy MariaDB adatbázissal lett implementálva, ahol a séma inicializálás flyway-el történik.
