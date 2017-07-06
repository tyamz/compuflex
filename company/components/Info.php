<?php namespace Compuflex\Company\Components;

use Auth;
use Illuminate\Support\Facades\DB as Db;
use Compuflex\Company\Models\Company;
use Cms\Classes\ComponentBase;

class Info extends ComponentBase
{
    public function componentDetails()
    {
        return [
            'name'        => 'Company Info',
            'description' => 'Provides Company Information on Page(s)'
        ];
    }

    public function defineProperties()
    {
        return [];
    }

    public function onRun() {
      if(!Auth::getUser()) {
  			echo 'Oops! Redirecting to Login!';
  		} else {
  			$user = Auth::getUser()->id;
        $company = Db::table('users')->select('company_id')->where('id', $user)->get();
  			$this->page['company'] = Company::find($company[0]->company_id);
      }
    }
}
