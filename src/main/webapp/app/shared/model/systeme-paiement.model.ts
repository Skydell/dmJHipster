import { ICompte } from 'app/shared/model/compte.model';

export interface ISystemePaiement {
  id?: number;
  crediteur?: string;
  debiteur?: string;
  methode?: string;
  comptes?: ICompte[];
}

export class SystemePaiement implements ISystemePaiement {
  constructor(
    public id?: number,
    public crediteur?: string,
    public debiteur?: string,
    public methode?: string,
    public comptes?: ICompte[]
  ) {}
}
