<?php namespace Compuflex\Downloads\Components;

use Auth;
use Cms\Classes\ComponentBase;
use Rainlab\User\Models\User;

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
			$this->downloads = User::find($user)->file;
		}
    }
}