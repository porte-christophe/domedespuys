package fr.simplon.domedespuys.service.interfaces;

import fr.simplon.domedespuys.dto.PurchasePostDTO;
import fr.simplon.domedespuys.dto.PurchasePutDTO;
import fr.simplon.domedespuys.entities.Purchase;

public interface IPurchaseService extends
        ICreate<Purchase, PurchasePostDTO>,
        IUpdate<Purchase, PurchasePutDTO>,
        IGetItem<Purchase>,
        ICollection<Purchase>
{}
