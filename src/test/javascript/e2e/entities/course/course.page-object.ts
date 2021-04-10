import { element, by, ElementFinder } from 'protractor';

export class CourseComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-course div table .btn-danger'));
  title = element.all(by.css('jhi-course div h2#page-heading span')).first();
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

export class CourseUpdatePage {
  pageTitle = element(by.id('jhi-course-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  clientnameInput = element(by.id('field_clientname'));
  coursiernameInput = element(by.id('field_coursiername'));
  timerequiredInput = element(by.id('field_timerequired'));

  panierSelect = element(by.id('field_panier'));
  compteSelect = element(by.id('field_compte'));
  restaurantSelect = element(by.id('field_restaurant'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setClientnameInput(clientname: string): Promise<void> {
    await this.clientnameInput.sendKeys(clientname);
  }

  async getClientnameInput(): Promise<string> {
    return await this.clientnameInput.getAttribute('value');
  }

  async setCoursiernameInput(coursiername: string): Promise<void> {
    await this.coursiernameInput.sendKeys(coursiername);
  }

  async getCoursiernameInput(): Promise<string> {
    return await this.coursiernameInput.getAttribute('value');
  }

  async setTimerequiredInput(timerequired: string): Promise<void> {
    await this.timerequiredInput.sendKeys(timerequired);
  }

  async getTimerequiredInput(): Promise<string> {
    return await this.timerequiredInput.getAttribute('value');
  }

  async panierSelectLastOption(): Promise<void> {
    await this.panierSelect.all(by.tagName('option')).last().click();
  }

  async panierSelectOption(option: string): Promise<void> {
    await this.panierSelect.sendKeys(option);
  }

  getPanierSelect(): ElementFinder {
    return this.panierSelect;
  }

  async getPanierSelectedOption(): Promise<string> {
    return await this.panierSelect.element(by.css('option:checked')).getText();
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

export class CourseDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-course-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-course'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
