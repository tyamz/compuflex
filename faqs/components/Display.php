<?php namespace Compuflex\Faqs\Components;

use Auth;
use Illuminate\Support\Facades\DB as Db;
use Cms\Classes\ComponentBase;
use Compuflex\Faqs\Models\Question;
use Compuflex\Faqs\Models\Answer;

class Display extends ComponentBase
{
    public function componentDetails()
    {
        return [
            'name'        => 'Display FAQs',
            'description' => 'A small component to display the Frequently Asked Questions and Answers'
        ];
    }

    public function defineProperties()
    {
        return [];
    }

    public function onRun()
    {
      $this->page['answers'] = Answer::all();
      if(!Auth::getUser()) {
  			echo 'Oops! Redirecting to Login!';
  		} else {
  			$user = Auth::getUser()->id;
        $company = Db::table('users')->select('company_id')->where('id', $user)->get();
  			$this->page['questions'] = Db::table('compuflex_faqs_questions')->where('company_id', $company[0]->company_id)->get();
        $this->page['answers'] = Db::table('compuflex_faqs_answers')->get();
      }
    }
}
