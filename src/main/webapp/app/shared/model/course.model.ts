import { IPanier } from 'app/shared/model/panier.model';
import { ICompte } from 'app/shared/model/compte.model';
import { IRestaurant } from 'app/shared/model/restaurant.model';

export interface ICourse {
  id?: number;
  clientname?: string;
  coursiername?: string;
  timerequired?: number;
  panier?: IPanier;
  compte?: ICompte;
  restaurant?: IRestaurant;
}

export class Course implements ICourse {
  constructor(
    public id?: number,
    public clientname?: string,
    public coursiername?: string,
    public timerequired?: number,
    public panier?: IPanier,
    public compte?: ICompte,
    public restaurant?: IRestaurant
  ) {}
}
