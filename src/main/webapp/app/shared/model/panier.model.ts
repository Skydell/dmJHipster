import { IRestaurant } from 'app/shared/model/restaurant.model';
import { IProduit } from 'app/shared/model/produit.model';
import { ICourse } from 'app/shared/model/course.model';
import { ICompte } from 'app/shared/model/compte.model';

export interface IPanier {
  id?: number;
  nbelements?: number;
  listelements?: string;
  price?: number;
  restaurants?: IRestaurant[];
  produits?: IProduit[];
  course?: ICourse;
  compte?: ICompte;
}

export class Panier implements IPanier {
  constructor(
    public id?: number,
    public nbelements?: number,
    public listelements?: string,
    public price?: number,
    public restaurants?: IRestaurant[],
    public produits?: IProduit[],
    public course?: ICourse,
    public compte?: ICompte
  ) {}
}
