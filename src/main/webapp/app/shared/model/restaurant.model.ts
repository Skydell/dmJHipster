import { ICompte } from 'app/shared/model/compte.model';
import { IProduit } from 'app/shared/model/produit.model';
import { ICourse } from 'app/shared/model/course.model';
import { ICooperative } from 'app/shared/model/cooperative.model';
import { IPanier } from 'app/shared/model/panier.model';

export interface IRestaurant {
  id?: number;
  name?: string;
  adress?: string;
  products?: string;
  compte?: ICompte;
  produits?: IProduit[];
  courses?: ICourse[];
  cooperative?: ICooperative;
  paniers?: IPanier[];
}

export class Restaurant implements IRestaurant {
  constructor(
    public id?: number,
    public name?: string,
    public adress?: string,
    public products?: string,
    public compte?: ICompte,
    public produits?: IProduit[],
    public courses?: ICourse[],
    public cooperative?: ICooperative,
    public paniers?: IPanier[]
  ) {}
}
