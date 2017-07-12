<?php namespace Compuflex\Downloads\Components;

use Auth;
use Cms\Classes\ComponentBase;
use Compuflex\Company\Models\Company;
use Illuminate\Support\Facades\DB as Db;

class Downloads extends ComponentBase
{
    public $downloads;

    public function componentDetails()
    {
        return [
            'name'        => 'Downloads Component',
            'description' => 'Simple Download Manager'
        ];
    }

    public function defineProperties()
    {
        return [
            'downloadssettings' => [
                'description'       => 'Download Manager Component Settings',
                'title'             => 'Download Manager Component Settings',
                'default'           => '',
                'type'              => 'string',
                'validationPattern' => '',
                'validationMessage' => 'This is the Validation Message.'
            ]
        ];
    }

    public function onRender()
    {

    }

    function onInit()
    {

    }

    public function onRun()
    {
		if(!Auth::getUser()) {
			echo 'Oops! Redirecting to Login!';
		} else {
			$user = Auth::getUser()->id;
      $company = Db::table('users')->select('company_id')->where('id', $user)->get();
			$this->downloads = Company::find($company[0]->company_id)->file;
		}
    }
}
