import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'roles',
        loadChildren: () => import('./roles/roles.module').then(m => m.DmJHipsterRolesModule),
      },
      {
        path: 'compte',
        loadChildren: () => import('./compte/compte.module').then(m => m.DmJHipsterCompteModule),
      },
      {
        path: 'produit',
        loadChildren: () => import('./produit/produit.module').then(m => m.DmJHipsterProduitModule),
      },
      {
        path: 'panier',
        loadChildren: () => import('./panier/panier.module').then(m => m.DmJHipsterPanierModule),
      },
      {
        path: 'restaurant',
        loadChildren: () => import('./restaurant/restaurant.module').then(m => m.DmJHipsterRestaurantModule),
      },
      {
        path: 'course',
        loadChildren: () => import('./course/course.module').then(m => m.DmJHipsterCourseModule),
      },
      {
        path: 'systeme-paiement',
        loadChildren: () => import('./systeme-paiement/systeme-paiement.module').then(m => m.DmJHipsterSystemePaiementModule),
      },
      {
        path: 'cooperative',
        loadChildren: () => import('./cooperative/cooperative.module').then(m => m.DmJHipsterCooperativeModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class DmJHipsterEntityModule {}
