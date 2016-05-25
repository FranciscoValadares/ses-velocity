db.emailtemplate.save([
    {companyName:"YourCompanyName",emailType:"NEW_MEMBER_WITH_REGISTERED_EMAIL" ,  name :"NEW_MEMBER_WITH_REGISTERED_EMAIL", fileName:"new_member_register_email.vm", active:true, mailFrom:"info@YourCompanyName.com", mailSubject:"Please confirm your email" },
    {companyName:"YourCompanyName",emailType:"NEW_MEMBER" ,  name:"NEW_MEMBER", fileName:"new_member.vm", active: true, mailFrom:"info@YourCompanyName.com", mailSubject:"Wellcome MyCompany" },
]);