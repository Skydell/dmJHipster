import { IPanier } from 'app/shared/model/panier.model';
import { ISystemePaiement } from 'app/shared/model/systeme-paiement.model';

export interface ICompte {
  id?: number;
  name?: string;
  surname?: string;
  age?: number;
  accountid?: string;
  adress?: string;
  role?: string;
  paniers?: IPanier[];
  restaurantId?: number;
  courseId?: number;
  systemePaiements?: ISystemePaiement[];
}

export class Compte implements ICompte {
  constructor(
    public id?: number,
    public name?: string,
    public surname?: string,
    public age?: number,
    public accountid?: string,
    public adress?: string,
    public role?: string,
    public paniers?: IPanier[],
    public restaurantId?: number,
    public courseId?: number,
    public systemePaiements?: ISystemePaiement[]
  ) {}
}
