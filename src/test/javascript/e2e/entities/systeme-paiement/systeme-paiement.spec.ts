import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { SystemePaiementComponentsPage, SystemePaiementDeleteDialog, SystemePaiementUpdatePage } from './systeme-paiement.page-object';

const expect = chai.expect;

describe('SystemePaiement e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let systemePaiementComponentsPage: SystemePaiementComponentsPage;
  let systemePaiementUpdatePage: SystemePaiementUpdatePage;
  let systemePaiementDeleteDialog: SystemePaiementDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load SystemePaiements', async () => {
    await navBarPage.goToEntity('systeme-paiement');
    systemePaiementComponentsPage = new SystemePaiementComponentsPage();
    await browser.wait(ec.visibilityOf(systemePaiementComponentsPage.title), 5000);
    expect(await systemePaiementComponentsPage.getTitle()).to.eq('dmJHipsterApp.systemePaiement.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(systemePaiementComponentsPage.entities), ec.visibilityOf(systemePaiementComponentsPage.noResult)),
      1000
    );
  });

  it('should load create SystemePaiement page', async () => {
    await systemePaiementComponentsPage.clickOnCreateButton();
    systemePaiementUpdatePage = new SystemePaiementUpdatePage();
    expect(await systemePaiementUpdatePage.getPageTitle()).to.eq('dmJHipsterApp.systemePaiement.home.createOrEditLabel');
    await systemePaiementUpdatePage.cancel();
  });

  it('should create and save SystemePaiements', async () => {
    const nbButtonsBeforeCreate = await systemePaiementComponentsPage.countDeleteButtons();

    await systemePaiementComponentsPage.clickOnCreateButton();

    await promise.all([
      systemePaiementUpdatePage.setCrediteurInput('crediteur'),
      systemePaiementUpdatePage.setDebiteurInput('debiteur'),
      systemePaiementUpdatePage.setMethodeInput('methode'),
      // systemePaiementUpdatePage.compteSelectLastOption(),
    ]);

    expect(await systemePaiementUpdatePage.getCrediteurInput()).to.eq('crediteur', 'Expected Crediteur value to be equals to crediteur');
    expect(await systemePaiementUpdatePage.getDebiteurInput()).to.eq('debiteur', 'Expected Debiteur value to be equals to debiteur');
    expect(await systemePaiementUpdatePage.getMethodeInput()).to.eq('methode', 'Expected Methode value to be equals to methode');

    await systemePaiementUpdatePage.save();
    expect(await systemePaiementUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await systemePaiementComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last SystemePaiement', async () => {
    const nbButtonsBeforeDelete = await systemePaiementComponentsPage.countDeleteButtons();
    await systemePaiementComponentsPage.clickOnLastDeleteButton();

    systemePaiementDeleteDialog = new SystemePaiementDeleteDialog();
    expect(await systemePaiementDeleteDialog.getDialogTitle()).to.eq('dmJHipsterApp.systemePaiement.delete.question');
    await systemePaiementDeleteDialog.clickOnConfirmButton();

    expect(await systemePaiementComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
