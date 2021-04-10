import { element, by, ElementFinder } from 'protractor';

export class PanierComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-panier div table .btn-danger'));
  title = element.all(by.css('jhi-panier div h2#page-heading span')).first();
  noResult = element(by.id('no-result'));
  entities = element(by.id('entities'));

  async clickOnCreateButton(): Promise<void> {
    await this.createButton.click();
  }

  async clickOnLastDeleteButton(): Promise<void> {
    await this.deleteButtons.last().click();
  }

  async countDeleteButtons(): Promise<number> {
    return this.deleteButtons.count();
  }

  async getTitle(): Promise<string> {
    return this.title.getAttribute('jhiTranslate');
  }
}

export class PanierUpdatePage {
  pageTitle = element(by.id('jhi-panier-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  nbelementsInput = element(by.id('field_nbelements'));
  listelementsInput = element(by.id('field_listelements'));
  priceInput = element(by.id('field_price'));

  restaurantSelect = element(by.id('field_restaurant'));
  produitSelect = element(by.id('field_produit'));
  compteSelect = element(by.id('field_compte'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setNbelementsInput(nbelements: string): Promise<void> {
    await this.nbelementsInput.sendKeys(nbelements);
  }

  async getNbelementsInput(): Promise<string> {
    return await this.nbelementsInput.getAttribute('value');
  }

  async setListelementsInput(listelements: string): Promise<void> {
    await this.listelementsInput.sendKeys(listelements);
  }

  async getListelementsInput(): Promise<string> {
    return await this.listelementsInput.getAttribute('value');
  }

  async setPriceInput(price: string): Promise<void> {
    await this.priceInput.sendKeys(price);
  }

  async getPriceInput(): Promise<string> {
    return await this.priceInput.getAttribute('value');
  }

  async restaurantSelectLastOption(): Promise<void> {
    await this.restaurantSelect.all(by.tagName('option')).last().click();
  }

  async restaurantSelectOption(option: string): Promise<void> {
    await this.restaurantSelect.sendKeys(option);
  }

  getRestaurantSelect(): ElementFinder {
    return this.restaurantSelect;
  }

  async getRestaurantSelectedOption(): Promise<string> {
    return await this.restaurantSelect.element(by.css('option:checked')).getText();
  }

  async produitSelectLastOption(): Promise<void> {
    await this.produitSelect.all(by.tagName('option')).last().click();
  }

  async produitSelectOption(option: string): Promise<void> {
    await this.produitSelect.sendKeys(option);
  }

  getProduitSelect(): ElementFinder {
    return this.produitSelect;
  }

  async getProduitSelectedOption(): Promise<string> {
    return await this.produitSelect.element(by.css('option:checked')).getText();
  }

  async compteSelectLastOption(): Promise<void> {
    await this.compteSelect.all(by.tagName('option')).last().click();
  }

  async compteSelectOption(option: string): Promise<void> {
    await this.compteSelect.sendKeys(option);
  }

  getCompteSelect(): ElementFinder {
    return this.compteSelect;
  }

  async getCompteSelectedOption(): Promise<string> {
    return await this.compteSelect.element(by.css('option:checked')).getText();
  }

  async save(): Promise<void> {
    await this.saveButton.click();
  }

  async cancel(): Promise<void> {
    await this.cancelButton.click();
  }

  getSaveButton(): ElementFinder {
    return this.saveButton;
  }
}

export class PanierDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-panier-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-panier'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
